package net.optifine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.init.Blocks;

public class ListQuadsOverlay
{
    private List<BakedQuad> listQuads = new ArrayList();
    private List<IBlockState> listBlockStates = new ArrayList();
    private List<BakedQuad> listQuadsSingle = Arrays.<BakedQuad>asList(new BakedQuad[1]);

    public void addQuad(BakedQuad quad, IBlockState blockState)
    {
        if (quad != null)
        {
            this.listQuads.add(quad);
            this.listBlockStates.add(blockState);
        }
    }

    public int size()
    {
        return this.listQuads.size();
    }

    public BakedQuad getQuad(int index)
    {
        return (BakedQuad)this.listQuads.get(index);
    }

    public IBlockState getBlockState(int index)
    {
        return index >= 0 && index < this.listBlockStates.size() ? (IBlockState)this.listBlockStates.get(index) : Blocks.air.getDefaultState();
    }

    public List<BakedQuad> getListQuadsSingle(BakedQuad quad)
    {
        this.listQuadsSingle.set(0, quad);
        return this.listQuadsSingle;
    }

    public void clear()
    {
        this.listQuads.clear();
        this.listBlockStates.clear();
    }
}
