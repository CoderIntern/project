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
public class EnergySystemTest {
    //将要构建的能量世界中能量盒子数量
    public static final int BOX_AMOUNT = 100;
    //每个盒子初始能量
    public static final double INITIAL_ENERGY = 1000;

    public static void main(String[] args){
        EnergySystem eng = new EnergySystem(BOX_AMOUNT, INITIAL_ENERGY);
        for (int i = 0; i < BOX_AMOUNT; i++){
            EnergyTransferTask task = new EnergyTransferTask(eng, i, INITIAL_ENERGY);
            Thread t = new Thread(task,"TransferThread_"+i);
            t.start();
        }
    }

}
