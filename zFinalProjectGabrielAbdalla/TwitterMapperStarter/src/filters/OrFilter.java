package filters;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * A filter that represents the logical of one of its children filter
 */
public class OrFilter implements Filter {
    private final Filter leftString;
    private final Filter rightString;

    public OrFilter(Filter leftString, Filter rightString) {
        this.leftString = leftString;
        this.rightString = rightString;
    }

    /**
     * A filter matches when one of its Strings does
     * @param s     the tweet to check
     * @return      whether one of them matches
     */
    @Override
    public boolean matches(Status s) {
        return leftString.matches(s) || rightString.matches(s);
    }

    @Override
    public List<String> terms() {
        List<String> orterms = new ArrayList<>();
        orterms.add(leftString.terms().get(0));
        orterms.add(rightString.terms().get(0));
        return orterms;
    }

    public String toString() {
        return "(" + leftString.toString() + " or " + rightString.toString() + ")";
//        return leftString.toString() + " or " + rightString.toString();
//        return String.valueOf(leftString) + " or " + String.valueOf(rightString);
    }
}
