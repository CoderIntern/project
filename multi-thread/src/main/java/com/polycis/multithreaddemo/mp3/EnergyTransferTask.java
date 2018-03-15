package com.polycis.multithreaddemo.mp3;

/**
 * <p> Date             :2018/1/10 </p>
 * <p> Module           : </p>
 * <p> Description      : </p>
 * <p> Remark           : </p>
 *
 * @author yangdejun
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public class EnergyTransferTask implements Runnable {
    //共享的能量世界
    private EnergySystem energySystem;
    //能量转移的源能量盒子下标
    private int fromBox;
    //单次能量转移最大单元
    private double maxAmount;
    //最大休眠时间（毫秒）
    private int DELAY = 10;

    public EnergyTransferTask(EnergySystem energySystem, int from, double max){
        this.energySystem = energySystem;
        this.fromBox = from;
        this.maxAmount = max;
    }

    public void run() {
        try{
            while (true){
                int toBox = (int) (energySystem.getBoxAmount()* Math.random());
                double amount = maxAmount * Math.random();
                energySystem.transfer(fromBox, toBox, amount);
                Thread.sleep((int) (DELAY * Math.random()));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}

