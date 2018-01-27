package com.brubix.reference.service.subject;

        import com.brubix.reference.controller.subject.SubjectForm;

        import java.util.List;

public interface SubjectQueryHandler {

    List<SubjectForm.SubjectData> findAllSubjects();
}
