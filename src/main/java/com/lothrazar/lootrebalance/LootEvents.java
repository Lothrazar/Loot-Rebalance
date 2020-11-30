package com.lothrazar.lootrebalance;

import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LootEvents {

  @SubscribeEvent
  public void onEntityInteract(EntityInteract event) {
    event.setResult(Result.DEFAULT);
    //TODO: well, there is no tool type, or item data tag for SHEARS so i am stuck
    //    if (!event.getWorld().isRemote &&
    //        event.getTarget() instanceof SheepEntity &&
    //        event.getTarget().isAlive() &&
    //        event.getPlayer().getHeldItem(event.getHand()).getItem() == Items.SHEARS) {
    //      SheepEntity sheep = (SheepEntity) event.getTarget();
    //      List<ItemStack> drops = sheep.onSheared(event.getPlayer(), event.getItemStack(), event.getWorld(), event.getPos(), 3);
    //      ExampleMod.LOGGER.info("SHeep " + drops.size());
    //      Random rand = event.getWorld().rand;
    //      drops.forEach(d -> {
    //                ItemEntity ent = event.getTarget().entityDropItem(d, 1.0F);
    //                ent.setMotion(ent.getMotion().add((rand.nextFloat() - rand.nextFloat()) * 0.1F, rand.nextFloat() * 0.05F, (rand.nextFloat() - rand.nextFloat()) * 0.1F));
    //      });
    //    }
  }
}
