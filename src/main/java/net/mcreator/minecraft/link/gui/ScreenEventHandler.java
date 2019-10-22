/*
 * Copyright 2018 Pylo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.mcreator.minecraft.link.gui;

import net.java.games.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(value = { Dist.CLIENT }) public class ScreenEventHandler {

	/**
	 * This method subsribes to screen draw events so the Link notice can be rendered on some of the screens.
	 *
	 * @param drawScreenEvent ScreenEvent.DrawScreenEvent event instance
	 */
	@OnlyIn(Dist.CLIENT) @SubscribeEvent public static void drawScreenEvent(
			GuiScreenEvent.DrawScreenEvent drawScreenEvent) {
		if (drawScreenEvent.getGui() instanceof MainMenuScreen || drawScreenEvent
				.getGui() instanceof IngameMenuScreen) {
			int color = drawScreenEvent.getGui() instanceof MainMenuScreen ? 0x000000 : 0xffffff;
			Minecraft.getInstance().fontRenderer.drawString("MCreator Link 1.2", 3, 3, color);
			Minecraft.getInstance().fontRenderer.drawString(I18n.format("link.menu.settingskey"), 3, 14, color);

			if (GLFW.glfwGetKey(Minecraft.getInstance().mainWindow.getHandle(), GLFW.GLFW_KEY_L) == GLFW.GLFW_PRESS)
				Minecraft.getInstance().displayGuiScreen(new GuiMCreatorLink(drawScreenEvent.getGui()));
		}
	}

}
