package com.brubix.reference.service.subject;

        import com.brubix.reference.controller.subject.SubjectRequest;

        import java.util.List;

public interface SubjectQueryHandler {

    List<SubjectRequest.SubjectData> findAllSubjects();
}
