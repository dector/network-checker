Network checker
===============

Simple test task solution

Install
=======

  1. Create MySQL database at localhost with name `checker`.
  2. Permit user `user` (pass `pass`) to CREATE, UPDATE, INSERT,
TRUNCATE.
  3. Execute `CREATE TABLE hosts (url varchar(15), state boolean)`.
  4. `git clone git://github.com/dector/network-checker.git`.
  5. `cd network-manager`.
  6. Run `mvn package`.
  7. Deploy `target/network-checker.war` to your server.
  8. ???
  9. PROFIT!!!

Estimating time
===============

  __Estimated__ time: ~ 4 - 4.5 h.
  __Real__ time: ~ 11.5 h.
  Diff: ~ 2.5 __times__.

Estimating __failed__ (with x3 coefficient).
