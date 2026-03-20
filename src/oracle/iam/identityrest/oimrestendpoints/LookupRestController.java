package oracle.iam.identityrest.oimrestendpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import oracle.iam.identityrest.lookups.LookupResult;
import oracle.iam.identityrest.lookups.LookuprestServiceImpl;

@Path("/api/v1")
public class LookupRestController {

    @GET
    @Produces({"application/json"})
    @Path("/lookup")
    public List<LookupResult> getLookupValues(@QueryParam("name") String name,@Context HttpServletResponse response) throws Exception {
        System.out.println("getLookup");
        Map<String, String> lookupMap = null;
        try {
            lookupMap = LookuprestServiceImpl.getLookupValues(name);
        } catch (Exception e) {
            System.out.println("ERROR:" + e.getMessage());
            //String errJson = "{\"error\":\"" + escapeJson(e.getMessage()) + "\"}";
            response.sendError(500, e.getMessage());
        }
        List<LookupResult> result = new ArrayList();
        
        Set<String> keys = lookupMap.keySet();
        for(String  key : keys)
        {
            LookupResult res = new LookupResult(key,lookupMap.get(key));
            result.add(res);
        }
        return result;
    }

    /**
     * Create a lookup definition (lookup code/type).
     * Example lookupCode: Lookup.AMPS.Roles.RoleType
     */
    @POST
    @Path("/lookups/{lookupCode}")
    public Response createLookup(@PathParam("lookupCode") String lookupCode) {
        try {
            LookuprestServiceImpl.createLookup(lookupCode);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    /**
     * Create a lookup value/entry within an existing lookup.
     * Uses query params to avoid requiring a JSON binding setup.
     */
    @POST
    @Path("/lookups/{lookupCode}/values")
    @Produces({"application/json"})
    public Response createLookupValue(
        @PathParam("lookupCode") String lookupCode,
        @QueryParam("valueKey") String valueKey,
        @QueryParam("valueDecode") String valueDecode
    ) {
        if (valueKey == null || valueKey.trim().isEmpty()) {
            return Response.status(400).entity("Missing required query param: valueKey").build();
        }
        if (valueDecode == null) {
            return Response.status(400).entity("Missing required query param: valueDecode").build();
        }

        try {
            LookuprestServiceImpl.addLookupValue(valueKey, valueDecode, lookupCode);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    /**
     * Update an existing lookup value's decoded/label value.
     * The encoded key is immutable (part of the path).
     */
    @PUT
    @Path("/lookups/{lookupCode}/values/{valueKey}")
    @Produces({"application/json"})
    public Response updateLookupValue(
        @PathParam("lookupCode") String lookupCode,
        @PathParam("valueKey") String valueKey,
        @QueryParam("valueDecode") String valueDecode
    ) {
        if (valueKey == null || valueKey.trim().isEmpty()) {
            return Response.status(400).entity("Missing required path param: valueKey").build();
        }
        if (valueDecode == null) {
            return Response.status(400).entity("Missing required query param: valueDecode").build();
        }

        try {
            LookuprestServiceImpl.updateLookupValue(valueKey, valueDecode, lookupCode);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    /**
     * Delete a lookup value/entry (actual delete, not disable).
     */
    @DELETE
    @Path("/lookups/{lookupCode}/values/{valueKey}")
    @Produces({"application/json"})
    public Response deleteLookupValue(
        @PathParam("lookupCode") String lookupCode,
        @PathParam("valueKey") String valueKey
    ) {
        if (valueKey == null || valueKey.trim().isEmpty()) {
            return Response.status(400).entity("Missing required path param: valueKey").build();
        }

        try {
            LookuprestServiceImpl.removeLookupValue(valueKey, lookupCode, true);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    
    

}
