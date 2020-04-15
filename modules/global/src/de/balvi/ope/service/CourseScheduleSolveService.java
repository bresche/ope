package de.balvi.ope.service;

import java.util.List;

public interface CourseScheduleSolveService {
    String NAME = "ope_CourseScheduleSolveService";

    List<String> solveCourseSchedule();
}