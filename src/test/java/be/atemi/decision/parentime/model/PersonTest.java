package be.atemi.decision.parentime.model;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

    @Test
    public void getAge() {
        Person person = Person.newInstance("John", "Doe", LocalDate.now().minusYears(36));
        Assert.assertEquals(person.getAge(), 36);
    }
}