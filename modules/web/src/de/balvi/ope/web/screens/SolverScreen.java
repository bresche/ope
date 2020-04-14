package de.balvi.ope.web.screens;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("ope_SolverScreen")
@UiDescriptor("solver-screen.xml")
public class SolverScreen extends Screen {

    @Inject
    private Notifications notifications;

    @Subscribe("solveAction")
    public void onSolveAction(Action.ActionPerformedEvent event) {
        notifications.create().withCaption("Hint").withDescription("Button pressed...").show();
    }

}