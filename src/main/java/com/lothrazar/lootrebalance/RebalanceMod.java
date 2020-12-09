package com.lothrazar.lootrebalance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RebalanceMod.MODID)
public class RebalanceMod {

  public static final String MODID = "lootrebalance";
  public static final Logger LOGGER = LogManager.getLogger();

  public RebalanceMod() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
  }

  private void setup(final FMLCommonSetupEvent event) {
    MinecraftForge.EVENT_BUS.register(new LootEvents());
  }
}
