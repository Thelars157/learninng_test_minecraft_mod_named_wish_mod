package net.Thelars.wish_mod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

    public class stone_wish_oreblock extends Block {
      
        public static final IntegerProperty NORTH = IntegerProperty.create("north", -4, 14);
        public static final IntegerProperty SOUTH = IntegerProperty.create("south", -4, 14);
        public static final IntegerProperty EAST = IntegerProperty.create("east", -4, 14);
        public static final IntegerProperty WEST = IntegerProperty.create("west", -4, 14);
        public static final IntegerProperty UP = IntegerProperty.create("up", -4, 14);
        public static final IntegerProperty DOWN = IntegerProperty.create("down", -4, 14);

        public stone_wish_oreblock(Properties properties) {
            super(properties);
           
            this.registerDefaultState(this.stateDefinition.any()
                    .setValue(NORTH, 0).setValue(SOUTH, 0)
                    .setValue(EAST, 0).setValue(WEST, 0)
                    .setValue(UP, 0).setValue(DOWN, 0));
        }



        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
                    builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
        }

        @Override
        public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
       
            VoxelShape outerShape = Shapes.block();

            VoxelShape innerShape = Shapes.box(
                    (8 - state.getValue(WEST)) / 16.0,
                    (8 - state.getValue(DOWN)) / 16.0,
                    (8 - state.getValue(NORTH)) / 16.0,
                    (8 + state.getValue(EAST)) / 16.0,
                    (8 + state.getValue(UP)) / 16.0,
                    (8 + state.getValue(SOUTH)) / 16.0
            );

            return Shapes.or(outerShape, innerShape);
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
         
            return Shapes.box(
                    (8 - state.getValue(WEST)) / 16.0,
                    (8 - state.getValue(DOWN)) / 16.0,
                    (8 - state.getValue(NORTH)) / 16.0,
                    (8 + state.getValue(EAST)) / 16.0,
                    (8 + state.getValue(UP)) / 16.0,
                    (8 + state.getValue(SOUTH)) / 16.0
            );
        }




        @Override
        public void attack(BlockState state, Level world, BlockPos pos, Player player) {
            ItemStack heldItem = player.getMainHandItem();
            
            if (heldItem.getItem() == Items.SLIME_BALL || heldItem.getItem() == Items.ARROW) {

                BlockHitResult hitResult = (BlockHitResult) player.pick(20.0D, 0.0F, false);
                IntegerProperty faceProp = getFaceProperty(hitResult.getDirection());

                int currentValue = state.getValue(faceProp);
                int newValue;

                if (heldItem.getItem() == Items.SLIME_BALL) {
                 
                    newValue = Math.max(currentValue - 1, -4);
                } else {

                    newValue = Math.min(currentValue + 1, 14);
                }

               
                world.setBlock(pos, state.setValue(faceProp, newValue), 3);
            }
        }

        private IntegerProperty getFaceProperty(Direction direction) {
            switch (direction) {
                case NORTH: return NORTH;
                case SOUTH: return SOUTH;
                case EAST: return EAST;
                case WEST: return WEST;
                case UP: return UP;
                case DOWN: return DOWN;
                default: throw new IllegalArgumentException("Invalid direction");
            }
        }
    }




