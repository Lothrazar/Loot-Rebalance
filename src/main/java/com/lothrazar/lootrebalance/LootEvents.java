package com.lothrazar.lootrebalance;

import java.util.Map;
import java.util.Random;
import com.google.common.collect.Maps;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LootEvents {

  //LOL why would they make this private, its already final who cares 
  //copied from net.minecraft.entity.passive.SheepEntity
  private static final Map<DyeColor, IItemProvider> WOOL_BY_COLOR = Util.make(Maps.newEnumMap(DyeColor.class), (p_203402_0_) -> {
    p_203402_0_.put(DyeColor.WHITE, Blocks.WHITE_WOOL);
    p_203402_0_.put(DyeColor.ORANGE, Blocks.ORANGE_WOOL);
    p_203402_0_.put(DyeColor.MAGENTA, Blocks.MAGENTA_WOOL);
    p_203402_0_.put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL);
    p_203402_0_.put(DyeColor.YELLOW, Blocks.YELLOW_WOOL);
    p_203402_0_.put(DyeColor.LIME, Blocks.LIME_WOOL);
    p_203402_0_.put(DyeColor.PINK, Blocks.PINK_WOOL);
    p_203402_0_.put(DyeColor.GRAY, Blocks.GRAY_WOOL);
    p_203402_0_.put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL);
    p_203402_0_.put(DyeColor.CYAN, Blocks.CYAN_WOOL);
    p_203402_0_.put(DyeColor.PURPLE, Blocks.PURPLE_WOOL);
    p_203402_0_.put(DyeColor.BLUE, Blocks.BLUE_WOOL);
    p_203402_0_.put(DyeColor.BROWN, Blocks.BROWN_WOOL);
    p_203402_0_.put(DyeColor.GREEN, Blocks.GREEN_WOOL);
    p_203402_0_.put(DyeColor.RED, Blocks.RED_WOOL);
    p_203402_0_.put(DyeColor.BLACK, Blocks.BLACK_WOOL);
  });
  final int MINWOOL_BONUS = 1;
  final int MAXWOOL_BONUS = 8;

  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onEntityInteract(EntityInteract event) {
    event.setResult(Result.DEFAULT);
    //TODO: well, there is no tool type, or item data tag for SHEARS so i am stuck
    World world = event.getWorld();
    Entity target = event.getTarget();
    if (!world.isRemote &&
        target instanceof SheepEntity &&
        target.isAlive() &&
        event.getPlayer().getHeldItem(event.getHand()).getItem() == Items.SHEARS) {
      SheepEntity sheep = (SheepEntity) target;
      if (sheep.isShearable() == false) {
        return;//cant
      }
      int stack = MINWOOL_BONUS + world.rand.nextInt(MAXWOOL_BONUS);
      ItemStack drop = new ItemStack(WOOL_BY_COLOR.get(sheep.getFleeceColor()), stack);
      System.out.println("sheep drop " + drop);
      ItemEntity ent = target.entityDropItem(drop, 1.0F);
      Random rand = world.rand;
      ent.setMotion(ent.getMotion().add((rand.nextFloat() - rand.nextFloat()) * 0.1F, rand.nextFloat() * 0.05F, (rand.nextFloat() - rand.nextFloat()) * 0.1F));
    }
  }
}
