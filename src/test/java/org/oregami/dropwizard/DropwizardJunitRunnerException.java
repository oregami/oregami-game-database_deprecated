package org.oregami.dropwizard;

public class DropwizardJunitRunnerException extends RuntimeException {

	private static final long serialVersionUID = -6906197837878271304L;

	public DropwizardJunitRunnerException(String s) {
        super(s);
    }

    public DropwizardJunitRunnerException(Exception e) {
        super(e);
    }

    public DropwizardJunitRunnerException(String s, Exception e) {
        super(s, e);
    }
}
