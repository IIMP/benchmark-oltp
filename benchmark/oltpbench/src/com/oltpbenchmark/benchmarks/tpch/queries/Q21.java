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

package com.oltpbenchmark.benchmarks.tpch.queries;

import com.oltpbenchmark.api.SQLStmt;

public class Q21 extends GenericQuery {

    public final SQLStmt query_stmt = new SQLStmt(
              "select "
            +     "s_name, "
            +     "count(*) as numwait "
            + "from "
            +     "supplier, "
            +     "lineitem l1, "
            +     "orders, "
            +     "nation "
            + "where "
            +     "s_suppkey = l1.l_suppkey "
            +     "and o_orderkey = l1.l_orderkey "
            +     "and o_orderstatus = 'F' "
            +     "and l1.l_receiptdate > l1.l_commitdate "
            +     "and exists ( "
            +         "select "
            +             "* "
            +         "from "
            +             "lineitem l2 "
            +         "where "
            +             "l2.l_orderkey = l1.l_orderkey "
            +             "and l2.l_suppkey <> l1.l_suppkey "
            +     ") "
            +     "and not exists ( "
            +         "select "
            +             "* "
            +         "from "
            +             "lineitem l3 "
            +         "where "
            +             "l3.l_orderkey = l1.l_orderkey "
            +             "and l3.l_suppkey <> l1.l_suppkey "
            +             "and l3.l_receiptdate > l3.l_commitdate "
            +     ") "
            +     "and s_nationkey = n_nationkey "
            +     "and n_name = 'SAUDI ARABIA' "
            + "group by "
            +     "s_name "
            + "order by "
            +     "numwait desc, "
            +     "s_name"
        );

    protected SQLStmt get_query() {
        return query_stmt;
    }
}
