package de.balvi.ope.web.screens;

import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import de.balvi.ope.web.actions.ProblemSolveAction;

import javax.inject.Inject;

@UiController("ope_SolverScreen")
@UiDescriptor("solver-screen.xml")
public class SolverScreen extends Screen {

    @Inject
        private Button problemSolveButton;

        @Subscribe
        public void onInit(InitEvent event) {
            problemSolveButton.setAction(new ProblemSolveAction());
    }

}