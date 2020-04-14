package de.balvi.ope.core.optaplanner;

import org.eclipse.persistence.jpa.jpql.tools.model.query.LikeExpressionStateObject;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@PlanningSolution
public class AscendingCountPlanningSolution {

    @PlanningEntityCollectionProperty
    List<IntValuePlanningEntity> ints;

    @PlanningScore
    HardSoftScore score;

}
