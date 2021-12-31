package filters;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A filter that represents the logical of both of its children filter
 */
public class AndFilter implements Filter {
    private final Filter leftString;
    private final Filter rightString;

    public AndFilter(Filter leftString, Filter rightString) {
        this.leftString = leftString;
        this.rightString = rightString;
    }

    /**
     * A filter matches when both of its Strings do
     * @param s     the tweet to check
     * @return      whether both of them match
     */
    @Override
    public boolean matches(Status s) {
        return leftString.matches(s) && rightString.matches(s);
    }

    @Override
    public List<String> terms() {
        List<String> andterms = new ArrayList<>();
//        andterms.add(String.valueOf(leftString));
//        andterms.add(String.valueOf(rightString));
        andterms.add(leftString.terms().get(0));
        andterms.add(rightString.terms().get(0));
        return andterms;
    }

    public String toString() {
        return "(" + leftString.toString() + " and " + rightString.toString() + ")";
//        return leftString.toString() + " and " + rightString.toString();
//        return String.valueOf(leftString) + " and " + String.valueOf(rightString);
    }

}
