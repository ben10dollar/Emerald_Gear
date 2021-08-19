package ben10dollar.emeraldgear.item;

import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class EmeraldBow extends BowItem {

    public static ToolMaterial toolMaterial;
    public static int extraDamage;

    public static final byte PIERCE_LEVEL = 2;
    public static final double VELOCITY_MULTIPLIER = 3;
    public static final int MAX_USE_TIME = 72000/10;
    public static final int field_30855 = 20;
    public static final int RANGE = 15 * 2;

    public EmeraldBow(ToolMaterial toolMaterial,  Item.Settings settings) {
        super(settings);
        this.toolMaterial = toolMaterial;
        extraDamage = (int)toolMaterial.getAttackDamage() / 2;
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) user;
            boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemStack = playerEntity.getArrowType(stack);
            if (!itemStack.isEmpty() || bl) {
                if (itemStack.isEmpty()) { itemStack = new ItemStack(Items.ARROW); }

                int i = this.getMaxUseTime(stack) - remainingUseTicks;
                float f = getPullProgress(i);
                if (!((double) f < 0.1D)) {
                    boolean bl2 = bl && itemStack.isOf(Items.ARROW);
                    if (!world.isClient) {

                        ArrowItem arrowItem = (ArrowItem) (itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
                        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity);
                        persistentProjectileEntity.setProperties(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 3.0F, 1.0F);
                        if (f == 1.0F) { persistentProjectileEntity.setCritical(true); }


                        //enchantments
                        if (EnchantmentHelper.getLevel(Enchantments.POWER, stack) > 0)
                            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double) EnchantmentHelper.getLevel(Enchantments.POWER, stack) * 0.5D + 0.5D);
                        if (EnchantmentHelper.getLevel(Enchantments.PUNCH, stack) > 0)
                            persistentProjectileEntity.setPunch(EnchantmentHelper.getLevel(Enchantments.POWER, stack));
                        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0)
                            persistentProjectileEntity.setOnFireFor(100);


                        //extra effects
                        persistentProjectileEntity.setPierceLevel(PIERCE_LEVEL);


                        //damage
                        persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + extraDamage);
                        stack.damage(1, (LivingEntity) playerEntity, (p) -> { p.sendToolBreakStatus(playerEntity.getActiveHand()); });


                        if (bl2 || playerEntity.getAbilities().creativeMode && (itemStack.isOf(Items.SPECTRAL_ARROW) || itemStack.isOf(Items.TIPPED_ARROW))) {
                            persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                        }

                        world.spawnEntity(persistentProjectileEntity);
                    }

                    world.playSound((PlayerEntity) null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!bl2 && !playerEntity.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                        if (itemStack.isEmpty())
                            playerEntity.getInventory().removeOne(itemStack);
                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
    }

    public static float getPullProgress(int useTicks) {
        float f = (float) useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F)
            f = 1.0F;

        return (float)(f * VELOCITY_MULTIPLIER);
    }


    //getters
    public int getMaxUseTime(ItemStack stack) { return MAX_USE_TIME; }
    public UseAction getUseAction(ItemStack stack) { return UseAction.BOW; }
    public int getRange() { return RANGE; }
    public Predicate<ItemStack> getProjectiles() { return BOW_PROJECTILES; }


    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        boolean bl = !user.getArrowType(itemStack).isEmpty();
        if (!user.getAbilities().creativeMode && !bl) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }


}