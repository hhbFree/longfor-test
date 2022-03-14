//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.longfor.shopping.plugin.mq.customer;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskConsumerAssignor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskConsumerAssignor.class);
    public static Integer partition = 6;

    public TaskConsumerAssignor() {
    }

    public static int getPartition() {
        return partition;
    }

    public static List<PartitionBean> getAssignor() {
        List<String> serverEndpointList = new ArrayList();
        return getAssignor(getPartition(), serverEndpointList, "", "");
    }

    public static List<PartitionBean> getAssignor(int partitionSize, List list, String endpoint, String pre) {
        List<PartitionBean> assignorList = new ArrayList();
        if (list != null && list.size() != 0) {
            int consumerSize = list.size();
            int n = partitionSize / consumerSize;
            int m = partitionSize % consumerSize;
            int partitionAssignor = n;
            int index = 0;
            if (index < m) {
                partitionAssignor = n + 1;
            }

            for(int i = index; i < partitionSize && i - index <= partitionAssignor; ++i) {
                PartitionBean partitionBean = new PartitionBean();
                partitionBean.setName(pre + i);
                partitionBean.setIndex(i);
                assignorList.add(partitionBean);
            }

            return assignorList;
        } else {
            return assignorList;
        }
    }
}
