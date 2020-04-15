package de.balvi.ope.web.screens;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import de.balvi.ope.service.CourseScheduleSolveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

@UiController("ope_SolverScreen")
@UiDescriptor("solver-screen.xml")
public class SolverScreen extends Screen {
    private static final Logger log = LoggerFactory.getLogger(SolverScreen.class);

    @Inject
    private Notifications notifications;
    @Inject
    private CourseScheduleSolveService courseScheduleSolveService;

    @Subscribe("solveAction")
    public void onSolveAction(Action.ActionPerformedEvent event) {
        notifications.create().withDescription("Start solving...").show();

        List<String> solveCourseSchedule = courseScheduleSolveService.solveCourseSchedule();

        String result = String.join("\n", solveCourseSchedule);
        notifications.create().withCaption("Solved!").withDescription(result).show();

        log.info(result);
    }


}