# OIMRestJar

`OIMRestJar` is the OIM-facing REST layer used for lookup operations.

It wraps OIM lookup utility calls and exposes HTTP endpoints for:
- reading lookup values
- creating lookup types
- creating/updating/deleting lookup entries



## Project Purpose

The service centralizes lookup writes into one API so other applications do not write directly to local tables.

Current design intent:
- lookups are managed in OIM
- lookup entry delete is a real delete
- no active/inactive toggle semantics
- no endpoint for deleting a full lookup type

## Implemented Endpoints

Base path: `/api/v1`

### Legacy read endpoint
- `GET /lookup?name=<lookupCode>`
  - Returns a list of key/value pairs from OIM lookup utility.

### Lookup type endpoint
- `POST /lookups/{lookupCode}`
  - Creates a lookup definition/type in OIM.
  - Example: `Lookup.AMPS.Roles.RoleType`

### Lookup value endpoints
- `POST /lookups/{lookupCode}/values?valueKey=...&valueDecode=...`
  - Creates a lookup entry.

- `PUT /lookups/{lookupCode}/values/{valueKey}?valueDecode=...`
  - Updates decode/label for an existing key.
  - `valueKey` is treated as immutable.

- `DELETE /lookups/{lookupCode}/values/{valueKey}`
  - Deletes a lookup entry (hard delete).

## Key Classes

- `src/oracle/iam/identityrest/oimrestendpoints/LookupRestController.java`
  - REST endpoint definitions and request validation.

- `src/oracle/iam/identityrest/lookups/LookuprestServiceImpl.java`
  - Service wrapper that delegates to `OIMlookupUtilities`.

## Dependencies
12C Jars
jersey-core-1.18
jersey-client-1.18
jersey-servlet-1.18
jersey-server-1.18
javax.servlet.javax.servlet-api

14C Jars
jersey-client-2.45
jakarta.ws.rs-api-2.1.5
jakarta.xml.bind-api
jakarta.servlet-api-4.0.2
## Build

This project uses the NetBeans/Ant build layout.

From `OIMRestJar` directory:

```bash
ant clean
ant jar
```

Main Ant file:
- `build.xml`

