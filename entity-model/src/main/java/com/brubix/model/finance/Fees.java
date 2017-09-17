package com.brubix.model.finance;

import com.brubix.model.inventory.Parent;
import com.brubix.model.inventory.Student;

import java.util.List;

/**
 * Created by sanjeev.singh1 on 12/09/17.
 */
public class Fees {

    Integer id;

    Student forStudent;

    Parent paidByParent;

    List<FeeSchedule> feeScheduleList;

    private class FeeSchedule{

    }
}
