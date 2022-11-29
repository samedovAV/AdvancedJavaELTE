package examPreporations.game;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum Piece {
    ROOKIE((x, y) -> {
        return true;
    }),
    KINGY((x, y) -> {
        return true;
    }),
    BISHY((x, y) -> {
        return true;
    }),
    HORSEY((x, y) -> {
        return true;
    });

    private final BiFunction<Pos, Pos, Boolean> mayMakeMove;

    Piece(BiFunction<Pos, Pos, Boolean> mayMakeMove) {
        this.mayMakeMove = mayMakeMove;
    }
}
