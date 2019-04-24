package be.atemi.decision.parentime.helper;

import be.atemi.decision.parentime.model.*;
import org.joda.time.LocalDate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public final class CircleFileReader {

    private static final CircleFileReader INSTANCE = new CircleFileReader();

    private CircleFileReader() {
    }

    public Circle read(String filePath) {

        Circle circle = null;

        try {

            System.out.println("reading the " + filePath + " file ...");

            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();

            URL url = getClass().getResource("/" + filePath);
            File file = Paths.get(url.toURI()).toFile();
            final Document document = builder.parse(file);

            System.out.println("- version: " + document.getXmlVersion());
            System.out.println("- encoding: " + document.getXmlEncoding());
            System.out.println("- standalone: " + document.getXmlStandalone());

            final Element root = document.getDocumentElement();
            circle = Circle.newInstance(root.getAttribute("name"));
            System.out.println("- circle name: " + circle.getName());

            System.out.println("# -------[ planning-config ]");

            final Element config = (Element) root.getElementsByTagName("planning-config").item(0);

            int days = Integer.valueOf(config.getElementsByTagName("days").item(0).getTextContent());
            System.out.println("- days: " + days);
            int timeslots = Integer.valueOf(config.getElementsByTagName("timeslots").item(0).getTextContent());
            System.out.println("- timeslots: " + timeslots);

            Config cfg = new Config(days, timeslots);
            circle.setConfig(cfg);

            System.out.println("# -------[ persons ]");

            NodeList persons = root.getElementsByTagName("person");

            Map<String, Person> personsMap = new HashMap<>();

            for (int i = 0; i < persons.getLength(); i++) {
                final Element person = (Element) persons.item(i);
                String id = person.getAttribute("id");
                String firstName = person.getAttribute("firstName");
                String lastName = person.getAttribute("lastName");
                String dob = person.getAttribute("dob");
                Person p = Person.newInstance(firstName, lastName, LocalDate.parse(dob));
                p.setLabel(id);
                personsMap.put(id, p);
                System.out.println("- person " + id + ": " + firstName + " " + lastName + " (" + dob + ")");
            }

            System.out.println("# ------- [ agendas ]");

            NodeList agendas = root.getElementsByTagName("agenda");

            for (int i = 0; i < agendas.getLength(); i++) {
                final Element agenda = (Element) agendas.item(i);
                Agenda a = new Agenda(days, timeslots);
                NodeList entries = agenda.getElementsByTagName("entry");
                for (int j = 0; j < entries.getLength(); j++) {
                    final Element entry = (Element) entries.item(j);
                    String d = entry.getAttribute("day");
                    String t = entry.getAttribute("timeslot");
                    String e = entry.getTextContent();
                    a.setEntry(Integer.valueOf(d), Integer.valueOf(t), Agenda.Entry.from(e));
                }

                String personId = agenda.getAttribute("personId");
                Person p = personsMap.get(personId);

                System.out.println("- agenda \"" + agenda.getAttribute("name") + "\" assigned to " + p.getFirstName() + " " + p.getLastName());

                p.setAgenda(a);
            }

            System.out.println("# ------- [ parental-links ]");

            final Element links = (Element) root.getElementsByTagName("parental-links").item(0);

            NodeList tutors = links.getElementsByTagName("tutor");

            for (int i = 0; i < tutors.getLength(); i++) {

                final Element tutor = (Element) tutors.item(i);
                final String tutorId = tutor.getAttribute("id");

                Person t = personsMap.get(tutorId);

                NodeList children = tutor.getElementsByTagName("child");

                for (int j = 0; j < children.getLength(); j++) {

                    final Element child = (Element) children.item(j);
                    final String childId = child.getAttribute("id");

                    Person c = personsMap.get(childId);
                    t.addChild(c);

                    System.out.println("- tutor \"" + t.getFirstName() + " " + t.getLastName() + "\" has been defined as the parent of the child \"" + c.getFirstName() + " " + c.getLastName() + "\"");
                }

            }

            System.out.println("# ------- [ stepfamilies ]");

            NodeList stepfamilies = root.getElementsByTagName("stepfamily");

            for (int i = 0; i < stepfamilies.getLength(); i++) {

                final Element stepfamily = (Element) stepfamilies.item(i);
                final String name = stepfamily.getAttribute("name");

                Stepfamily sf = Stepfamily.newInstance(name);

                tutors = stepfamily.getElementsByTagName("tutor");

                for (int j = 0; j < tutors.getLength(); j++) {

                    final Element tutor = (Element) tutors.item(j);
                    final String tutorId = tutor.getAttribute("id");

                    Person t = personsMap.get(tutorId);
                    sf.addTutor(t);

                    System.out.println("- tutor \"" + t.getFirstName() + " " + t.getLastName() + "\" has been added to stepfamily " + sf.getName());
                }

                System.out.println("--> stepfamily \"" + sf.getName() + " has been added to circle " + circle.getName());

                circle.addStepfamily(sf);
            }


        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final URISyntaxException e) {
            e.printStackTrace();
        }

        return circle;
    }

    public static CircleFileReader getInstance() {
        return INSTANCE;
    }
}
