package al.nya.reflect.features.modules.Visual.hud.implement;

import al.nya.reflect.events.events.client.EventShader;
import al.nya.reflect.features.modules.Visual.hud.HUDObject;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.utils.render.RoundedUtil;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.render.FontRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumChatFormatting;
import al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard.Score;
import al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard.ScoreObjective;
import al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard.ScorePlayerTeam;
import al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard.Scoreboard;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreboardHUD extends HUDObject {
    private int HEIGHT = 0;
    private int X = 0;
    private int Y = 0;
    private boolean editing;

    public ScoreboardHUD() {
        super(0, 0, 60, "Scoreboard");
    }

    @Override
    public boolean doDraw() {
        return enable.getValue() && !mc.getTheWorld().getScoreboard().isNull();
    }

    @Override
    public void drawHUD(int x, int y, float p, boolean isEditing) {
        editing = isEditing;
        WorldClient world = mc.getTheWorld();
        FontRenderer font = mc.getFontRenderer();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        Minecraft mc = Minecraft.getMinecraft();
        Scoreboard scoreboard = world.getScoreboard();
        ScoreObjective scoreobjective = new ScoreObjective(null);
        ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(thePlayer.getName());

        if (!scoreplayerteam.isNull()) {
            int i1 = EnumChatFormatting.getColorIndex(scoreplayerteam.getChatFormat());

            if (i1 >= 0) {
                scoreobjective = scoreboard.getObjectiveInDisplaySlot(3 + i1);
            }
        }

        ScoreObjective scoreobjective1 = !scoreobjective.isNull() ? scoreobjective
                : scoreboard.getObjectiveInDisplaySlot(1);

        if (!scoreobjective1.isNull()) {
            Collection<Score> collection = scoreboard.getSortedScores(scoreobjective1);
            /*ArrayList<Score> arraylist = Lists.newArrayList(Iterables.filter(collection, new Predicate() {

                public boolean apply(Score p_apply_1_) {
                    return p_apply_1_.getPlayerName() != null && !p_apply_1_.getPlayerName().startsWith("#");
                }

                public boolean apply(Object p_apply_1_) {
                    return this.apply((Score) p_apply_1_);
                }
            }));
             */
            //Use stream filter
            List<Score> arraylist = new ArrayList<Score>(collection);
            arraylist = arraylist.stream().filter(S -> S.getPlayerName() != null && !S.getPlayerName().startsWith("#")).collect(Collectors.toList());
            List<Score> arraylist1;
            if (arraylist.size() > 15) {
                arraylist1 = new ArrayList<>(arraylist);
                arraylist1 = arraylist1.stream().skip(arraylist.size() - 15).collect(Collectors.toList());
            } else {
                arraylist1 = arraylist;
            }

            int height = 8;
            title = "Scoreboard";
            float width = font.getStringWidth(title) + 10;
            int height2 = 0;
            for (int i = -2; i < arraylist1.size(); i++) {
                height2 += font.getFontHeight() + 1;
            }
            HEIGHT = height2;
            X = x;
            Y = y;
            String s3 = scoreobjective1.getDisplayName();
            font.drawStringWithShadow(s3, (float) ((x + (getWidth() / 2)) - font.getStringWidth(s3) / 2), (float) y + 3, 0xffffffff);

            for (int i = 0; i < arraylist1.size(); i++) {
                Score score1 = arraylist1.get(arraylist1.size() - i - 1);
                ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
                String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName());

//                for (Mapper m : Sb.mappers) {
//                    s1 = s1.replaceAll(m.getKey(), m.getValue());
//                }

                width = Math.max(font.getStringWidth(s1), width);
                font.drawString(s1, x + 4, y + (height += 10), 0xffffffff);
            }

            setWidth((int) Math.max(font.getStringWidth(s3), width) + 10);
        }
    }

    @Override
    public void onBlur(EventShader event) {
        float y = (isSpecial() || editing) ? Y - 18 : Y;
        float add = (isSpecial() || editing) ? 18 : 0;
        this.translate.interpolate(X, y, 0.2f);
        RoundedUtil.drawRoundOutline(translate.getX(), translate.getY(), getWidth(), HEIGHT + add + 2, 2, .5f, ColorUtils.applyOpacity(Color.WHITE, .85f), ColorUtils.applyOpacity(Color.WHITE, .85f));
        RoundedUtil.drawRound(translate.getX(), translate.getY(), getWidth(), HEIGHT + add + 2, 2, false, Color.WHITE);
    }
}
