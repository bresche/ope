package de.balvi.ope.core.courseschedule;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.ArrayList;
import java.util.List;

@PlanningSolution
public class CourseSchedule {

    private List<Integer> roomList = new ArrayList<>();
    private List<Integer> periodList = new ArrayList<>();
    private List<Lecture> lectureList = new ArrayList<>();
    private HardSoftScore score;

    @ValueRangeProvider(id = "availableRooms")
    @ProblemFactCollectionProperty
    public List<Integer> getRoomList() {
        return roomList;
    }

    @ValueRangeProvider(id = "availablePeriods")
    @ProblemFactCollectionProperty
    public List<Integer> getPeriodList() {
        return periodList;
    }

    @PlanningEntityCollectionProperty
    public List<Lecture> getLectureList() {
        return lectureList;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }
}

