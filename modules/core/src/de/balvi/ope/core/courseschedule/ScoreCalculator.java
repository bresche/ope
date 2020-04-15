package de.balvi.ope.core.courseschedule;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ScoreCalculator implements EasyScoreCalculator<CourseSchedule> {
    private static final Logger log = LoggerFactory.getLogger(ScoreCalculator.class);

    @Override
    public Score calculateScore(CourseSchedule courseSchedule) {
        int hardScore = 0;
        int softScore = 0;

        Set<String> occupiedRooms = new HashSet<>();
        for (Lecture lecture : courseSchedule.getLectureList()) {
            String roomInUse = lecture.getPeriod().toString() + ":" + lecture.getRoomNumber().toString();
            if (occupiedRooms.contains(roomInUse)) {
                hardScore += -1;
                log.info("hardScore: " + hardScore);
            } else {
                occupiedRooms.add(roomInUse);
                log.info("occupiedRooms added: " + roomInUse);
            }
        }

        return HardSoftScore.valueOf(hardScore, softScore);
    }
}
