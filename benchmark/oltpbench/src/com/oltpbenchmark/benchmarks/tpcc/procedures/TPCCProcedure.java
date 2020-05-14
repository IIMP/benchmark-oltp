/******************************************************************************
 *  Copyright 2015 by OLTPBenchmark Project                                   *
 *                                                                            *
 *  Licensed under the Apache License, Version 2.0 (the "License");           *
 *  you may not use this file except in compliance with the License.          *
 *  You may obtain a copy of the License at                                   *
 *                                                                            *
 *    http://www.apache.org/licenses/LICENSE-2.0                              *
 *                                                                            *
 *  Unless required by applicable law or agreed to in writing, software       *
 *  distributed under the License is distributed on an "AS IS" BASIS,         *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 *  See the License for the specific language governing permissions and       *
 *  limitations under the License.                                            *
 ******************************************************************************/

package com.oltpbenchmark.benchmarks.tpcc.procedures;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.benchmarks.tpcc.TPCCWorker;

public abstract class TPCCProcedure extends Procedure {

    public abstract ResultSet run(Connection conn, Random gen,
            int terminalWarehouseID, int numWarehouses,
            int terminalDistrictLowerID, int terminalDistrictUpperID,
            TPCCWorker w) throws SQLException;


public String parse_query(String query) throws SQLException {
	String out = "";

	String[] queryArr = query.trim().split("\\s++");
	for (int i = 0; i < queryArr.length; i++) {
		if ((queryArr[i].equals("+")) || (queryArr[i].equals("=")) || (queryArr[i].equals("<")) || (queryArr[i].equals(">="))) {
			out = out + " " + queryArr[i];
			if ((queryArr[i-1].equals(queryArr[i+1])) || (queryArr[i-1].equals("OL_DELIVERY_D")) || (queryArr[i+1].equals("OL_I_ID")) || (queryArr[i-1].equals("C_LAST"))|| (queryArr[i-1].equals("C_DATA"))) {
				out = out + " " + queryArr[i+1];
			}
			else {
				out = out + " '" + queryArr[i+1].replaceAll(",", "") + "'";
				if (queryArr[i+1].contains(","))
					out = out + ", ";
			}
			i = i+1;
		}
		else
			out = out + " " + queryArr[i] ;
	}
	return out;
}
}
