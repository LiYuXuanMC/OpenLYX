package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.EventType;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.events.events.EventTick;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.math.MathUtil;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C01PacketChatMessage;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02PacketUseEntity;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
// M2bt client 's Shit CODE
public class AutoL extends Module {

    List<String> ChatList = new ArrayList<>();
    List<String> ReportList = new ArrayList<>();
    List<String> waitPlayers = new ArrayList<>();
    public static ConcurrentHashMap<String, Integer> targetedPlayers = new ConcurrentHashMap<>();
    public OptionValue autoReport = new OptionValue("Auto Report", false);
    int timer = 0;
    public AutoL() {
        super("AutoL", ModuleType.Player);
        ReportList.add("飞行加速是gua");
        ReportList.add("外挂");
        ReportList.add("飞行");
        ReportList.add("杀戮");
        ReportList.add("骂人");
        ReportList.add("辱骂他人");
        ReportList.add("纪狗");
        ReportList.add("加速");
        ReportList.add("疑似飞行");
        ChatList.add("做人不能这样，缺狗粮才知道来找我？");
        ChatList.add("哟！你这是刚被雷过啊，还是准备去雷人啊。");
        ChatList.add("我感觉你像两头猪，因为一头猪已经不能形容你的蠢。");
        ChatList.add("听你说话，一种智商上的优越感油然而生。");
        ChatList.add("天下之大，大不过你缺的那块心眼。");
        ChatList.add("长的惊险……有创意啊。");
        ChatList.add("你丫活生生就是抽象派典藏啊。");
        ChatList.add("你就不该被放出来。");
        ChatList.add("你长得真诚恳，很艺术……");
        ChatList.add("你活着也忒有勇气了把。");
        ChatList.add("洗一洗你那自称爷的嘴脸");
        ChatList.add("大哥，把你脸上的分辨率调低点好吗？");
        ChatList.add("一脸兴冲冲的，跟喝了尿糖似的。");
        ChatList.add("和蟑螂共存活的超个体，生命力腐烂的半植物");
        ChatList.add("你还是蛮正常的,如果不考虑智商！");
        ChatList.add("王航派遣纪狗飘粘土吸引玩家");
        ChatList.add("我真的想给你的脑袋安一个抽水马桶……");
        ChatList.add("你的小脑真发达，把大脑的第二都占了");
        ChatList.add("讲个笑话：粘土上年九月更新反作弊");
        ChatList.add("会说话么？");
        ChatList.add("依我看你的病情应该在ICU了吧");
        ChatList.add("笑尿了");
        ChatList.add("认识你，真好，不用再去动物园了。");
        ChatList.add("你的长相，把我的网速都拖慢了");
        ChatList.add("你内张脸长地比盆骨都标志。");
        ChatList.add("一看你就是和瓶三鹿长大的。");
        ChatList.add("别仗着自己脂肪多，脸皮就厚！");
        ChatList.add("L");
        ChatList.add("fw");
        ChatList.add("B站关注嘉然今天吃什么");
        ChatList.add("#粘土无能管理组气急败坏#");
        ChatList.add("王航正在绞杀你最喜欢的服务器lmao");
        ChatList.add("王航不是老鼠，但你mother长得像老鼠");
        ChatList.add("记得多盖点土在身上");
        ChatList.add("OMG啊");
        ChatList.add("上帝创造你是他的创意，你能继续活下去是你的勇气。");
        ChatList.add("是金子总会发光，你这个玻璃总是反光");
        ChatList.add("你出生时你脑子和你的脐带一起剪断了");
        ChatList.add("我见过你在上厕所的时候");
        ChatList.add("站着别动，我给你去买个橘子");
        ChatList.add("你角质层这么薄，脸皮还能这么厚。");
        ChatList.add("你脑袋空不要紧，关键是不要进水了。");
        ChatList.add("现在的粘土这么智能吗，猪都能拱字了。");
        ChatList.add("你很会下厨吧，看你挺会添油加醋的。");
        ChatList.add("你肺活量一定很好吧，这么能吹。");
        ChatList.add("能抬起你的手吗，别用脚打了行不行。");
        ChatList.add("你属化肥的吗，给对面养这么肥。");
        ChatList.add("你脸皮这么厚，国家怎么不研究拿你脸皮做防弹衣呢。");
        ChatList.add("你挺会自由潜水吧，看你脑子里进了不少水。");
        ChatList.add("那么喜欢杠，你去打麻将啊。");
        ChatList.add("你长得真好看，好像螳螂，脸真小。");
        ChatList.add("我不是看不起你，而是压根就懒得理你。");
        ChatList.add("你的新欢，不照样是别人的破鞋。");
        ChatList.add("巴黎圣母院少个敲钟的，就你了。");
        ChatList.add("你一出门，千山鸟飞绝，万径人踪灭。");
        ChatList.add("你的话, 我连标点符号都不信。");
        ChatList.add("你咋不上天呢？");
        ChatList.add("说话前刷刷牙，OK?");
        ChatList.add("你什么品种 怎么这么凶");
        ChatList.add("你的脑子长着只是为了显高吗？");
        ChatList.add("咸鱼翻身有什么用，翻了身，还是咸鱼。");
        ChatList.add("闭着眼睛都觉得你丑。");
        ChatList.add("看你丑的份上，就当你对吧。");
        ChatList.add("你头这么大，里面都是水吗？");
        ChatList.add("祝您寿比昙花");
        ChatList.add("呦呦呦 急啦");
        ChatList.add("百度搜索不到你的名字搜狗可以");
        addValue(autoReport);
    }
    @Override
    public void onEnable() {
        targetedPlayers = new ConcurrentHashMap<>();
        this.waitPlayers.clear();
    }

    @Override
    public void onDisable() {
        targetedPlayers.clear();
        this.waitPlayers.clear();
    }

    public String getRandomFuck() {
        return ChatList.get(MathUtil.getRandom(0, ChatList.size()));
    }

    @EventTarget
    public void onClientTick(EventTick event) {
        if (mc.getTheWorld().isNull()) {
            this.waitPlayers.clear();
            return;
        }
        timer--;
        List<String> dellist = new ArrayList<>();
        List<Entity> allList = mc.getTheWorld().getLoadedEntityList();
        if (allList.size() <= 1) this.waitPlayers.clear();
        for (Entity entity : allList) {

            if (EntityPlayerSP.isEntityPlayerSP(entity) || !(EntityPlayer.isEntityPlayer(entity))) continue;

            for (int i = 0; i < this.waitPlayers.size(); i++) {
                if (Objects.equals(this.waitPlayers.get(i), entity.getName())) {
                    this.waitPlayers.remove(i);
                    dellist.add(entity.getName());
                }
            }

            final EntityPlayer player = new EntityPlayer(entity.getWrapObject());
            if (player.getHealth() <= 0.0f) {
                final String name2;
                if (!this.shouldAnnounce(name2 = player.getName())) {
                    continue;
                }
                this.doAnnounce(name2);
                break;
            }
        }

        boolean isFuck = false;

        for (int i = 0; i < this.waitPlayers.size(); i++) {
            doAnnounce(this.waitPlayers.get(i));
            //ChatUtils.message("Remove Player " + this.waitPlayers.get(i) + " (is Fuck done)");
            this.waitPlayers.remove(i);
            isFuck = true;
        }

        this.waitPlayers.addAll(dellist);

        if (isFuck) return;

        // old code
        targetedPlayers.forEach((name, timeout) -> {
            if (timeout <= 0) {
                targetedPlayers.remove(name);
            } else {
                targetedPlayers.put(name, timeout - 1);
            }
        });
    }
    @EventTarget
    public void onPacket(EventPacket packet) {
        if (mc.getThePlayer().isOnGround() && packet.getEventType() == EventType.SendPacket) {
            if (C02PacketUseEntity.isC02PacketUseEntity(packet.getPacket())) {
                C02PacketUseEntity attack = new C02PacketUseEntity(packet.getPacket().getWrapObject());
                if (attack.getAction() == C02Action.ATTACK) {
                    Entity entity = attack.getEntityFromWorld(mc.getTheWorld());

                    if (EntityPlayer.isEntityPlayer(entity)) {

                        String name = entity.getName();
                        if (name.contains("§")) return;
                        for (String tname : this.waitPlayers) {
                            if (Objects.equals(tname, name)) {
                                return; // 如果列表存在就返回
                            }
                        }
                        //ChatUtils.message("add " + name + " to list");
                        this.waitPlayers.add(name);
                    }
                }
                return;
            }
        }
        return;
    }


    private boolean shouldAnnounce(final String name) {
        return targetedPlayers.containsKey(name);
    }

    private void doAnnounce(final String name) {
        timer++;
        if (timer >= 6) return;
        targetedPlayers.remove(name);
        NetworkManager netManager = mc.getNetHandler().getNetworkManager();
        if (autoReport.getValue())
            netManager.sendPacket(new C01PacketChatMessage("/report " + name + " " + ReportList.get(MathUtil.getRandom(0, ReportList.size())))); // Auto Report

        netManager.sendPacket(new C01PacketChatMessage(("/t #1 " + getRandomFuck()).replaceAll("#1", name)));
    }

    public void addTargetedPlayer(final String name) {
        if (Objects.equals(name, mc.getThePlayer().getName())) {
            return;
        }
        targetedPlayers.put(name, 20);
    }
}
