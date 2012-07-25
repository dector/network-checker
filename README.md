Network checker
===============

Simple test task solution

Install
=======

  1. Create MySQL database at localhost with name `checker`.
  2. Permit user `user` (pass `pass`) to CREATE, UPDATE, INSERT,
TRUNCATE.
  3. Execute `CREATE TABLE hosts (url varchar(15), state boolean)`.
  4. Run `mvn package`.
  5. Deploy `target/network-checker.war` to your server.
  6. ???
  7. PROFIT!!!

Estimating time
===============

  __Estimated__ time: ~ 4 - 4.5 h.
  __Real__ time: ~ 11.5 h.
  Diff: ~ 2.5 __times__.

Estimating __failed__ (with x3 coefficient).
