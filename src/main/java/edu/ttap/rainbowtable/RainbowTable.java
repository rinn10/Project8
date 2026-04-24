package edu.ttap.rainbowtable;
import java.util.Map;
import java.util.function.Function;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * A rainbow table is a collection of chains of passwords for the purpose of
 * reversing hashes.
 */
public class RainbowTable {
    private Function<Password, Hash> hasher;
    private Function<Hash, Password> reducer;
    private Map<Password, Password> passwordMap;

    /**
     * Constructs a new rainbow table from an already-computed list of endpoints.
     * @param chains a list of password chains, pairs of starting and ending passwords.
     * @param hasher a function that maps a password to its hash
     * @param reducer a function that maps a hash to its password
     */
    public RainbowTable(
            List<Pair<Password, Password>> chains,
            Function<Password, Hash> hasher,
            Function<Hash, Password> reducer) {
        this.hasher= hasher;
        this.reducer= reducer;

        Map<Password, Password> passwordMap = new HashMap<>();

        for(int i=0; i<chains.size(); i++)
        {
            Pair<Password, Password> chain= chains.get(i);
            Password start = chain.first();
            Password end = chain.second();
            passwordMap.put(end, start);
        }
    }

    /**
     * Attempts to reverse the given hash according to the rainbow table algorithm.
     * 
     * @param h the hash to invert
     * @param maxSteps the maximum number of steps (hash-reduce cycles) to attempt
     * @return an Optional containing the password if found, or empty if not
     */
    public Optional<Password> invert(Hash h, int maxSteps) {
        String ans = "";
        Hash currentHash = h;

         for (int i = 0; i < maxSteps; i++) {
            if (i % 2 == 0) {
                Password possibleEnd = reducer.apply(currentHash);
                ans = possibleEnd.value();
            
                    if (passwordMap.containsKey(possibleEnd)) {
                        Password start = passwordMap.get(possibleEnd);
                        Password currentPassword = start;

                        for (int j = 0; j < maxSteps; j++) {
                            Hash newHash = hasher.apply(currentPassword);
                            if (newHash.equals(h)) 
                                return Optional.of(currentPassword);
                            currentPassword = reducer.apply(newHash);
                            }
                        }
                currentHash = hasher.apply(possibleEnd);
                } 
            else 
                h.hashCode();
            }
    return Optional.empty();
}
}