package com.lothrazar.lootrebalance;

import java.util.Map;
import java.util.Random;
import com.google.common.collect.Maps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.Util;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LootEvents {

  //LOL why would they make this private, its already final who cares 
  //copied from net.minecraft.entity.passive.SheepEntity
  private static final Map<DyeColor, ItemLike> ITEM_BY_DYE = Util.make(Maps.newEnumMap(DyeColor.class), (p_203402_0_) -> {
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
  static final int MINWOOL_BONUS = 1;
  static final int MAXWOOL_BONUS = 8;

  public static final Tags.IOptionalNamedTag<Item> SHEARS = ItemTags.createOptional(new ResourceLocation("forge", "shears"));

  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onEntityInteract(EntityInteract event) {
    event.setResult(Result.DEFAULT);
    Level world = event.getWorld();
    Entity target = event.getTarget();
    if (!world.isClientSide &&
        target instanceof Sheep &&
        target.isAlive() &&
        event.getPlayer().getItemInHand(event.getHand()).is(SHEARS)) {
      Sheep sheep = (Sheep) target;
      if (sheep.readyForShearing() == false) {
        return;
      }
      int stack = MINWOOL_BONUS + world.random.nextInt(MAXWOOL_BONUS);
      ItemStack drop = new ItemStack(ITEM_BY_DYE.get(sheep.getColor()), stack);
      ItemEntity ent = target.spawnAtLocation(drop, 1.0F);
      Random rand = world.random;
      ent.setDeltaMovement(ent.getDeltaMovement().add((rand.nextFloat() - rand.nextFloat()) * 0.1F, rand.nextFloat() * 0.05F, (rand.nextFloat() - rand.nextFloat()) * 0.1F));
    }
  }
}
