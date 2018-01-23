public class SchedulerTest {


//    /**
//     * 测试数据增量
//     */
//    @Test
//    public void testIncrement() {
//        try {
//            System.out.println(new Date());
//            SchedulingService schedulingService = new SchedulingService();
//            List list = schedulingService.getUpdateTime();
//            System.out.println(list.size());
//            if ((list.size() == 0) || (list == null)) {
//                System.out.println("error");
//                throw new JobExecutionException("获取上次的更新时间失败了，无法开启定时任务！");
//            } else {
//                System.out.println("correct");
//                Scheduling scheduling = (Scheduling) list.get(0);
//                Date updateTimeStart = scheduling.getUpdateTimeEnd();
//                Date updateTimeEnd = DateUtil.date();
//
//                MabangApiJob mAJ = new MabangApiJob();
//
//                //========  1.2, 1.3必须建立在1.1完成的基础上
//                mAJ.stockInfoApi(updateTimeStart, updateTimeEnd); //1.1
//                mAJ.stockWarehouseInfoApi(scheduling);//1.2
//                mAJ.stockMachiningInfoApi(scheduling);//1.3
//
//                mAJ.stockProviderInfoApi(updateTimeStart, updateTimeEnd);//1.4
//                mAJ.orderInfoApi(updateTimeStart, updateTimeEnd);//1.5
//                mAJ.fbaInfoApi(updateTimeStart, updateTimeEnd);//1.6
//                //========= 1.8必须建立在1.7完成的基础上
//                mAJ.productPurchaseInfoApi(updateTimeStart, updateTimeEnd);//1.7
//                mAJ.productPurchaseStorageInInfoApi(scheduling);//1.8
//
//                mAJ.stockStorageLogApi(updateTimeStart, updateTimeEnd);//1.9
//
//                //存入本次更新时间区间
//                scheduling.setUpdateTimeStart(updateTimeStart);
//                scheduling.setUpdateTimeEnd(updateTimeEnd);
//                schedulingService.add(scheduling);
//                System.out.println(scheduling);
//                System.out.println(updateTimeStart);
//                System.out.println(updateTimeEnd);
//                System.out.println("存入本次更新时间区间");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

}



