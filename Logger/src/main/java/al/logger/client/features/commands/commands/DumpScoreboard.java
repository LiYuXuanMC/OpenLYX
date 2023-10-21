package al.logger.client.features.commands.commands;

import al.logger.client.features.commands.Command;
import al.logger.client.utils.FileUtils;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.utils.scoreboard.Score;
import al.logger.client.wrapper.LoggerMC.utils.scoreboard.ScoreObjective;
import al.logger.client.wrapper.LoggerMC.utils.scoreboard.ScorePlayerTeam;
import al.logger.client.wrapper.LoggerMC.utils.scoreboard.Scoreboard;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

public class DumpScoreboard extends Command {
    public DumpScoreboard() {
        super("DumpScoreboard", "dumpScoreboard");
    }

    @Override
    public boolean trigger(String[] args) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        Scoreboard scoreboard = Minecraft.getMinecraft().getTheWorld().getScoreboard();
        ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(thePlayer.getName());
        StringBuilder builder = new StringBuilder();
        if (!scoreplayerteam.isNull()) {
            ScoreObjective scoreobjective = scoreboard.getObjectiveInDisplaySlot(1);
            builder.append("Scoreboard Display Name: ").append(scoreobjective.getDisplayName()).append("\n");
            Collection<Score> scores = scoreboard.getSortedScores(scoreobjective);
            for (Score score : scores) {
                ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score.getPlayerName());
                String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score.getPlayerName());
                builder.append(s1).append("\n");
            }
        }
        builder.append("All\n");
        for (Score score : scoreboard.getScores()) {
            ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score.getPlayerName());
            String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score.getPlayerName());
            builder.append(score.getPoints()).append(" ").append(score.getPlayerName()).append("\n");
        }
        FileUtils.writeFile(new File("scoredump.txt"),builder.toString().getBytes(StandardCharsets.UTF_8));
        return true;
    }

    @Override
    public void help() {

    }
}
