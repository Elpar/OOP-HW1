
package homework1;

import java.awt.*;
import static java.lang.Math.abs;

/**
 * This exception will be thrown if at Shape's setSize() method the dimensions given are impossible to create.
 * The class suggests an alternative possible size, one which is greater than 0 and positive.
 */
public class ImpossibleSizeException extends Exception {

    private Dimension suggestedDim;

    // Abstraction Function:
    // suggestedDim is a valid and legal suggested dimension if one is needed when the exception is thrown.

    // Representation Invariant:
    // suggestedDim.width > 0 && suggestedDim.height > 0

    /**
     * @modifies this
     * @effects calls the constructor of Exception and creates a new suggested legal dimension, which is the value
     * that dimension has but as an absolute value, integer and greater than 0.
     */
    public ImpossibleSizeException(Dimension dimension) {
        super();
        suggestedDim.width = abs((int)(dimension.getWidth()));
        suggestedDim.height = abs((int)(dimension.getHeight()));
        checkRep();
    }

    /**
     * @return the suggested dimension's value.
     */
    public Dimension getSuggestedDimension() {
        checkRep();
        return this.suggestedDim;
    }

    /**
     * @effects throws alerts on different assertions of the Representation Invariant
     * @throws AssertionError if the Representation Invariant is being violated.
     */
    private void checkRep() {
        assert suggestedDim.width > 0 : "The suggested dimension's width is invalid";
        assert suggestedDim.height > 0 : "The suggested dimension's height is invalid";
    }
}
