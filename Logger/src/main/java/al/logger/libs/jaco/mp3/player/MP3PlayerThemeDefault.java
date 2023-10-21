/*
 * Copyright (C) Cristian Sulea ( http://cristiansulea.entrust.ro )
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package al.logger.libs.jaco.mp3.player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The default theme for the mp3 player.
 * 
 * @version 0.1.0, June 16, 2011
 * @author Cristian Sulea ( http://cristiansulea.entrust.ro )
 */
final class MP3PlayerThemeDefault implements MP3PlayerTheme {

  public void apply(final MP3Player player) {

    final JButton playButton = new JButton();
    playButton.setText(">");
    playButton.setToolTipText("Play");
    playButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        player.play();
      }
    });

    final JButton pauseButton = new JButton();
    pauseButton.setText("||");
    pauseButton.setToolTipText("Pause");
    pauseButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        player.pause();
      }
    });

    final JButton stopButton = new JButton();
    stopButton.setText("#");
    stopButton.setToolTipText("Stop");
    stopButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        player.stop();
      }
    });

    final JButton skipBackwardButton = new JButton();
    skipBackwardButton.setText("|<");
    skipBackwardButton.setToolTipText("Skip Backward");
    skipBackwardButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        player.skipBackward();
      }
    });

    final JButton skipForwardButton = new JButton();
    skipForwardButton.setText(">|");
    skipForwardButton.setToolTipText("Skip Forward");
    skipForwardButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        player.skipForward();
      }
    });

    final JSlider volumeSlider = new JSlider();
    volumeSlider.setToolTipText("Volume");
    volumeSlider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        player.setVolume(volumeSlider.getValue());
      }
    });
    volumeSlider.setMinimum(0);
    volumeSlider.setMaximum(100);
    volumeSlider.setMajorTickSpacing(50);
    volumeSlider.setMinorTickSpacing(10);
    volumeSlider.setPaintTicks(true);
    volumeSlider.setPaintTrack(true);

    final JCheckBox repeatCheckBox = new JCheckBox();
    repeatCheckBox.setText("Repeat");
    repeatCheckBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        player.setRepeat(repeatCheckBox.isSelected());
      }
    });

    final JCheckBox shuffleCheckBox = new JCheckBox();
    shuffleCheckBox.setText("Shuffle");
    shuffleCheckBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        player.setShuffle(shuffleCheckBox.isSelected());
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(player);
    player.setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(playButton).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addComponent(pauseButton).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(stopButton).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(skipBackwardButton).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(skipForwardButton)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(shuffleCheckBox).addComponent(repeatCheckBox)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(volumeSlider, 0, 0, Short.MAX_VALUE))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(playButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(pauseButton).addComponent(stopButton).addComponent(skipBackwardButton).addComponent(skipForwardButton)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addComponent(shuffleCheckBox).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(repeatCheckBox)).addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

  }
}