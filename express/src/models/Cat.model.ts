import { Sequelize } from 'sequelize';
import db from '../db';

// @ts-ignore
const { DataTypes } = Sequelize;

const CatModel = db.define('cats', {
  cat_id: {
    type: DataTypes.BIGINT.UNSIGNED,
    primaryKey: true,
    autoIncrement: true,
    allowNull: false,
  },
  name: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  age: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
  color: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  origin_id: {
    type: DataTypes.BIGINT.UNSIGNED,
    allowNull: false,
  },
  breed_id: {
    type: DataTypes.BIGINT.UNSIGNED,
    allowNull: false,
  },
}, {
  timestamps: false,
});

export default CatModel;
