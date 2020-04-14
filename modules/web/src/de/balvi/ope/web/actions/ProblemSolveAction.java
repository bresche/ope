package de.balvi.ope.web.actions;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;

@org.springframework.stereotype.Component("ope_problemSolveAction")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProblemSolveAction extends BaseAction {

    public static final String ACTION_ID = "ope_problemSolveAction";


    @Inject
    Notifications notifications;

    public ProblemSolveAction() {
        super(ACTION_ID);
    }

    @Override
    public void actionPerform(Component component) {
        solve((Button)component);
    }

    private void solve(Button component) {
        component.setCaption("Solved");
    }
}
