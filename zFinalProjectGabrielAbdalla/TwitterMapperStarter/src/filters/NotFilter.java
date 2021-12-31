package filters;

import twitter4j.Status;

import java.util.List;

/**
 * A filter that represents the logical not of its child filter
 */
public class NotFilter implements Filter {
    private final Filter childString;

    public NotFilter(Filter child) {
        this.childString = child;
    }

    /**
     * A not filter matches when its child doesn't, and vice versa
     * @param s     the tweet to check
     * @return      whether or not it matches
     */
    @Override
    public boolean matches(Status s) {
        return !childString.matches(s);
    }

    @Override
    public List<String> terms() {
        return childString.terms();
    }

    public String toString() {
        return "not " + childString.toString();
    }
}
