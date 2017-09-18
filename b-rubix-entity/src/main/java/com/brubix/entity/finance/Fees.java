package com.brubix.entity.finance;

import com.brubix.entity.inventory.Parent;
import com.brubix.entity.inventory.Student;

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
