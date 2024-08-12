package com.chenx.enums;

import java.util.Random;

public enum RoShamBo implements Competitor<RoShamBo> {

    ROCK {
        @Override
        public Outcome compete(RoShamBo opponent) {
            return compete(SCISSORS, opponent);
        }
    }, SCISSORS {
        @Override
        public Outcome compete(RoShamBo opponent) {
            return compete(PAPER, opponent);
        }
    }, PAPER {
        @Override
        public Outcome compete(RoShamBo opponent) {
            return compete(ROCK, opponent);
        }
    };

    static Random rand = new Random();

    Outcome compete(RoShamBo loser, RoShamBo competitor) {
        return (competitor == this) ? Outcome.DRAW : (competitor == loser ? Outcome.WIN : Outcome.LOSE);
    }

    static RoShamBo random() {
        return RoShamBo.values()[rand.nextInt(RoShamBo.values().length)];
    }

    static Outcome match(RoShamBo a, RoShamBo b) {
        return a.compete(b);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            match(random(), random());
        }
    }
}

enum Outcome {
    LOSE, DRAW, WIN;
}

interface Competitor<T extends Competitor<T>> {
    Outcome compete(T competitor);
}