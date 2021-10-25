package ws.util;

import org.apache.ws.security.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 13.01.14
 */
public class PasswordProvider implements CallbackHandler {
    private Map<String,String> passwords = Collections.emptyMap();

    public void setPasswords(final Map<String, String> passwords) {
        this.passwords = new HashMap<String, String>(passwords.size());

        // convert usernames to lowercase to simplify search
        for (final Map.Entry<String,String> entry : passwords.entrySet()) {
            final String username = entry.getKey().toLowerCase();
            this.passwords.put(username, entry.getValue());
        }
    }

    @Override
    public void handle(final Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for( final Callback callback : callbacks) {
            if (callback instanceof WSPasswordCallback) {
                handlePasswordCallback((WSPasswordCallback) callback);
            }
        }
    }

    private void handlePasswordCallback(final WSPasswordCallback callback) throws UnsupportedCallbackException {
        final String username = callback.getIdentifier().toLowerCase();

        final String password = passwords.get(username);
        if (password == null) {
            throw new UnsupportedCallbackException(callback, "Unknown username: " + username);
        }

        callback.setPassword(password);
    }
}
