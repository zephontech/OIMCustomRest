

package oracle.iam.identityrest.lookups;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class LookuprestServiceImpl {
    
    public static Map<String,String> getLookupValues(String name) throws Exception
    {
        
        OIMlookupUtilities lookupOps = new OIMlookupUtilities();
        Map<String,String> data = lookupOps.getLookupValues(name);
        return data;
    }
    
    public static String getLookupValue(String key, String lookup) throws Exception
    {
        OIMlookupUtilities lookupOps = new OIMlookupUtilities();
        String data = lookupOps.getLookupValue(key,lookup);
        return data;
    }
    
    public static List<String> getLookupKeys(String value, String lookup) throws Exception
    {
        OIMlookupUtilities lookupOps = new OIMlookupUtilities();
        List<String> lkeys = new ArrayList();
        String[] keys = lookupOps.getLookupKeys(value, lookup);
        for(String key : keys)
        {
            lkeys.add(key);
        }
        return lkeys;
    }
    
    public static void addLookupValue(String Key, String Value, String Lookup) throws Exception
    {
        OIMlookupUtilities lookupOps = new OIMlookupUtilities();
        lookupOps.addLookupValue(Key, Value, Lookup);
    }
    
    public static void updateLookupValue(String Key, String Value, String Lookup, boolean ExactMatch) throws Exception
    {
        OIMlookupUtilities lookupOps = new OIMlookupUtilities();
        lookupOps.updateLookupValue(Key, Value, Lookup,ExactMatch);
    }
    
    public static void updateLookupValue(String Key, String Value, String Lookup) throws Exception
    {
        OIMlookupUtilities lookupOps = new OIMlookupUtilities();
        lookupOps.updateLookupValue(Key, Value, Lookup);
    }
    
    public static void removeLookupValue(String Key, String Lookup, boolean ExactMatch) throws Exception
    {
        OIMlookupUtilities lookupOps = new OIMlookupUtilities();
        lookupOps.removeLookupValue(Key,Lookup,ExactMatch);
    }
    
    public static void createLookup(String Lookup) throws Exception
    {
        OIMlookupUtilities lookupOps = new OIMlookupUtilities();
        lookupOps.createLookup(Lookup);
    }
    
    private static void clearLookup(String Lookup) throws Exception
    {
        OIMlookupUtilities lookupOps = new OIMlookupUtilities();
        lookupOps.clearLookup(Lookup);
    }
    
    private static void removeLookup(String Lookup) throws Exception
    {
        OIMlookupUtilities lookupOps = new OIMlookupUtilities();
        lookupOps.removeLookup(Lookup);
    }

}
