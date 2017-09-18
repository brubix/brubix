package com.brubix.entity.attention;

import java.util.Date;

/**
 * Created by sanjeev.singh1 on 12/09/17.
 */
public class Attendance {

    private Integer id;

    private Integer parentClass;

    private Integer parentId;

    private Date attendanceDate;

    private String status;

    private AbsenteeStatus absenteeStatus;

    private enum AbsenteeStatus{
        COULD_NOT_MARK,
        UNKNOWN,
        SICK_LEAVE,
        PLANNED_LEAVE,
        CASUAL_LEAVE
    }

}
