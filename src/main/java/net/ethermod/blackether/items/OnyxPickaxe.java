package net.ethermod.blackether.items;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.enums.EnumHelper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.util.List;

public class OnyxPickaxe extends PickaxeItem {
    private static final ToolMaterial ONYX_PICKAXE_MATERIAL = EnumHelper.getToolMaterial(500, 30f, 10f, 100, 100, Ingredient.ofItems(BlackEtherMod.ONYX_ORE));
    public OnyxPickaxe(Settings s) {
        super(ONYX_PICKAXE_MATERIAL, 0, 0, s);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("Onyx Pickaxe"));
    }
}
