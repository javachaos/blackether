package net.ethermod.blackether.items;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.enums.EnumHelper;
import net.ethermod.blackether.items.base.PickaxeBase;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.util.List;

public class OnyxPickaxe extends PickaxeBase {
    public OnyxPickaxe() {
        super(EnumHelper.getToolMaterial(
                500,
                30f,
                10f,
                100,
                100,
                 Ingredient.ofItems(BlackEtherMod.ONYX_ORE)));
    }
}
