#!/bin/bash

JAVA_BIN=/usr/bin/java
BDU_AC_BIN=/usr/local/bin/BDUAPIClient.jar
BDU_AC_MODE="-m poll"
BDU_AC_OWNER="bduadmin"

case "$1" in
	start)
		# BDU API Client poll start-up
        echo -n "Starting BDUAPIClient: "
        su - $BDU_AC_OWNER exec $JAVA_BIN -jar $BDU_AC_BIN $BDU_AC_MODE > /dev/null 2>&1 &
        echo $! > /tmp/bduapiclient.pid
        retval=$?
        if [[ $retval -eq 0 ]]; then
            echo "Ok"
        else 
            echo "Failed"
        fi
        ;;
    stop)
        # BDU API Client poll shutdown
        echo -n "Stopping BDUAPIClient polling mode..\n"
        #kill `cat /tmp/bduapiclient.pid`
        ;;
    reload|restart)
        $0 stop
        $0 start
        ;;
    *)
        echo "Usage $0 start|stop|restart"
        exit 1
esac

exit 0
