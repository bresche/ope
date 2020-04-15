package de.balvi.ope.core.integerexample;

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
