package de.balvi.ope.service;

import de.balvi.ope.core.courseschedule.CourseSchedule;
import de.balvi.ope.core.courseschedule.Lecture;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(CourseScheduleSolveService.NAME)
public class CourseScheduleSolveServiceBean implements CourseScheduleSolveService {

    public static final String JAVA_CONFIGURATION = "de/balvi/ope/core/courseschedule/courseScheduleSolverConfiguration.xml";
    public static final String DROOLS_CONFIGURATION = "de/balvi/ope/core/courseschedule/courseScheduleSolverWithDroolsConfiguration.xml";


    public List<String> solveCourseSchedule() {
        SolverFactory<CourseSchedule> solverFactory = SolverFactory
                .createFromXmlResource(JAVA_CONFIGURATION, getClass().getClassLoader()
                );

        return solveCourseScheduleFromSolverFactory(solverFactory);
    }

    @Override
    public List<String> solveCourseScheduleWithDrools() {
        SolverFactory<CourseSchedule> solverFactory = SolverFactory
                .createFromXmlResource(DROOLS_CONFIGURATION, getClass().getClassLoader()
                );

        return solveCourseScheduleFromSolverFactory(solverFactory);
    }

    private List<String> solveCourseScheduleFromSolverFactory(SolverFactory<CourseSchedule> solverFactory) {

        Solver<CourseSchedule> courseScheduleSolver = solverFactory.buildSolver();
        CourseSchedule unsolvedCourseSchedule = getUnsolvedCourseScheduleExampleData();
        CourseSchedule solvedCourseSchedule = courseScheduleSolver.solve(unsolvedCourseSchedule);
        return courseScheduleToString(solvedCourseSchedule.getLectureList());
    }

    public List<String> courseScheduleToString(List<Lecture> solvedCourseSchedule) {
        return solvedCourseSchedule.stream()
                .map(c -> "Lecture in Room "
                        + c.getRoomNumber().toString()
                        + " during Period " + c.getPeriod().toString()
                        + " by " + c.getTeacher())
                .collect(Collectors.toList());
    }


    /**
     * https://www.codeflow.site/de/article/opta-planner
     * <p>
     * result after 30sec solving with JAVA ScoreCalculator:
     * Lecture in Room 3 during Period 2 by Teacher 1
     * Lecture in Room 2 during Period 3 by Teacher 2
     * Lecture in Room 3 during Period 1 by Teacher 3
     * Lecture in Room 1 during Period 2 by Teacher 4
     * Lecture in Room 1 during Period 1 by Teacher 5
     * Lecture in Room 3 during Period 3 by Teacher 6
     * Lecture in Room 1 during Period 2 by Teacher 7
     * Lecture in Room 2 during Period 1 by Teacher 8
     * Lecture in Room 1 during Period 3 by Teacher 9
     * Lecture in Room 1 during Period 1 by Teacher 10
     * Lecture in Room 2 during Period 2 by Teacher 11
     * <p>
     * 10:23:15.145 INFO  o.o.c.i.l.DefaultLocalSearchPhase       - Local Search phase (1) ended: time spent (30005), best score (-2hard/0soft), score calculation speed (460/sec), step total (11935).
     * 10:23:15.145 INFO  o.o.core.impl.solver.DefaultSolver      - Solving ended: time spent (30005), best score (-2hard/0soft), score calculation speed (460/sec), phase total (2), environment mode (REPRODUCIBLE).
     * <p>
     * result after 30sec solving with drl Score Calculator:
     * <p>
     * 13:46:18.431 INFO  o.o.core.impl.solver.DefaultSolver      - Solving ended: time spent (30010), best score (-82hard/0soft), score calculation speed (239/sec), phase total (2), environment mode (REPRODUCIBLE).
     * 13:46:18.459 INFO  d.balvi.ope.web.screens.SolverScreen    - Lecture in Room 1 during Period 1 by Teacher 1
     * Lecture in Room 2 during Period 1 by Teacher 2
     * Lecture in Room 1 during Period 2 by Teacher 3
     * Lecture in Room 3 during Period 2 by Teacher 4
     * Lecture in Room 3 during Period 3 by Teacher 5
     * Lecture in Room 3 during Period 3 by Teacher 6
     * Lecture in Room 2 during Period 2 by Teacher 7
     * Lecture in Room 1 during Period 3 by Teacher 8
     * Lecture in Room 1 during Period 2 by Teacher 9
     * Lecture in Room 2 during Period 1 by Teacher 10
     * Lecture in Room 3 during Period 1 by Teacher 11
     *
     * @return
     */
    private CourseSchedule getUnsolvedCourseScheduleExampleData() {
        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.getPeriodList().add(1);
        courseSchedule.getPeriodList().add(2);
        courseSchedule.getPeriodList().add(3);

        courseSchedule.getRoomList().add(1);
        courseSchedule.getRoomList().add(2);
        courseSchedule.getRoomList().add(3);

        courseSchedule.getLectureList().add(createLecture("Teacher 1", 50));
        courseSchedule.getLectureList().add(createLecture("Teacher 2", 50));
        courseSchedule.getLectureList().add(createLecture("Teacher 3", 50));
        courseSchedule.getLectureList().add(createLecture("Teacher 4", 50));
        courseSchedule.getLectureList().add(createLecture("Teacher 5", 100));
        courseSchedule.getLectureList().add(createLecture("Teacher 6", 100));
        courseSchedule.getLectureList().add(createLecture("Teacher 7", 100));
//        courseSchedule.getLectureList().add(createLecture("Teacher 8"));
//        courseSchedule.getLectureList().add(createLecture("Teacher 9"));
//        courseSchedule.getLectureList().add(createLecture("Teacher 10"));
//        courseSchedule.getLectureList().add(createLecture("Teacher 11"));

        return courseSchedule;
    }

    private Lecture createLecture(String teacher, int students) {
        Lecture lecture = new Lecture();
        lecture.setTeacher(teacher);
        lecture.setStudents(students);
        return lecture;
    }

}