package com.eahackathon.watd.whosatthedoor;

import com.eahackathon.watd.whosatthedoor.models.UpdatePersonNameRequest;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by KienDu on 1/10/2016.
 */
public class UpdatePersonNameRequestUnitTest {

    public static String TEST_PERSON_NAME_REQUEST = "test_person_name";

    @Test
    public void testUpdatePersonNameRequest_getter_setter() {
        UpdatePersonNameRequest updatePersonNameRequest = new UpdatePersonNameRequest(TEST_PERSON_NAME_REQUEST);
        updatePersonNameRequest.setPersonName(TEST_PERSON_NAME_REQUEST);
        assertEquals(TEST_PERSON_NAME_REQUEST, updatePersonNameRequest.getPersonName());
    }
}
