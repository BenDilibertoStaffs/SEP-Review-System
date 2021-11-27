#!/bin/sh

/home/wait-for-it.sh cockroachdb:26257 -- ./cockroach sql --insecure --host=cockroachdb < /home/setup.sql