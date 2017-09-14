package com.brubix.model;

import java.util.List;

/**
 * Created by sanjeev.singh1 on 12/09/17.
 */
public class Fees {

    Integer id;

    Students forStudent;

    Parents paidByParent;

    List<FeeSchedule> feeScheduleList;

    private class FeeSchedule{

    }
}
